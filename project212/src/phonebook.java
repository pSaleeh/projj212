import java.util.*;
public class phonebook {
public linkedlist<Contact> LinkListConatact; 
public linkedlist<Event> LinkListEvent;
public Scanner input=new Scanner(System.in); //check

public phonebook() {// total is 2 big o(1)
	LinkListConatact = new linkedlist<>();//1
	LinkListEvent = new linkedlist<>();//1
}


public void searchcontact() {//big o(n^2) total =4n^2+38n+301
	if(LinkListConatact.empty()) { // 2
		System.out.println("list empty"); // 1
		return;      //1
	}
	String select; //1 new String for switch    
	int run = 0; //1 when run is 0 it means the user didnt input the right choice 
	while(run != 1)  {                 
	System.out.println("Enter search criteria:\r\n"
			+ "1. Name\r\n"
			+ "2. Phone Number\r\n"
			+ "3. Email Address\r\n"
			+ "4. Address\r\n"
			+ "5. Birthday\r\n"
			+ "Enter your choice: ");//1
	select = input.nextLine();//1
	switch(select) {   //1
		case "1": System.out.print("enter contact's name: ");    //1  finished case 1 for name 
			String currentname =  input.nextLine();            //1
			run = 1;   //n
			Contact contname =  searcbyname(currentname);//2n+9 contaname=1 searchbyname=2n+8
			
			if(contname != null) {//1
				System.out.println("Contact found!");//1
				System.out.println(contname.toString());//1
			}
			else //1
				System.out.print("cant find contact"); //1
			break; //1
			
		case "2": System.out.println("enter contact's phone"); //1 finished case 2 which is similar to case 1 
			String currentphone =  input.nextLine();//1
			run = 1; //change
			Contact contphone = searchbyphone(currentphone); //3n+9
			
			if(contphone != null) { //1
				System.out.println("Contact found!");//1
				System.out.println(contphone.toString());//2
			}
			else//1
				System.out.print("cant find contact");//1
			break;//1
			
		case "3": System.out.println("enter contact's email");//1
			String currentemail =  input.nextLine();//1
			run = 1;
			linkedlist<Contact> elist= new linkedlist<>(); //1 elist is the list of contacts with similar emails
			elist = searchbyeamil(currentemail);//8n+65
			
			if(elist != null) {//1
				System.out.println("Contacts found!");//1
				elist.findfirst();//2
				while(!elist.last()) {//n+2
					System.out.println(elist.retreive().toString());//n+2
					elist.findnext();//n+1
				}
				System.out.println(elist.retreive().toString());//1
			}
			else//1
				System.out.print("cant find contact");//1
			break;//1
			
		case "4": System.out.println("enter contact's adress");//1
		System.out.print("4");//1
		String currentadress =  input.nextLine();//1
		run = 1;
		linkedlist<Contact> alist= new linkedlist<>();//1
		alist = searchbyAdress(currentadress);//11n+71
		if(alist != null) {//1
			System.out.println("Contacts found!");//1
			while(!alist.last()) {//n+2
				System.out.println(alist.retreive().toString());//n+3
				alist.findnext();//n+1
			}
			System.out.println(alist.retreive().toString());//3
		}
		else//1
			System.out.print("cant find contact");//1
			break;//1
		case "5": //1
		System.out.println("enter contact's birth");//1
		System.out.println("5l");//1
		String currentbirth =  input.nextLine();//1
		System.out.println(currentbirth);//1
		run = 1;
		linkedlist<Contact> blist= new linkedlist<>();//1
		
		blist = searchbyBirthday(currentbirth);// 4n^2+7n+68
		if(blist != null) {//1
			System.out.println("Contacts found!");//1
			while(!blist.last()) {//n+2
				System.out.println(blist.retreive().toString());//n+2
				blist.findnext();//n+1
			}
			System.out.println(blist.retreive().toString());//1
		}
		else//1
			System.out.print("cant find contact");//1
			break;//1
		default://1
			System.out.println("Sorry wrong number");//1
		}
	}// when user inputs wrong number
}


public void menu() {
	String select;//1
	int run = 0;
	System.out.println("Welcome to the Linked Tree Phonebook!");//1
	do {
		System.out.print(
				"Please choose an option:\r\n"
				+ "1. Add a contact\r\n"
				+ "2. Search for a contact\r\n"
				+ "3. Delete a contact\r\n"
				+ "4. Schedule an event\r\n"
				+ "5. Print event details\r\n"
				+ "6. Print contacts by first name\r\n"
				+ "7. Print all events alphabetically\r\n"
				+ "8. Exit\r\n"
				+ "Enter your choice: ");//1
		 
		select=input.nextLine();//1

		switch(select) {//1
		case "1": Contact addcontacts = addcontact();//3n+15
			if(addcontacts != null) {//1
				LinkListConatact.Insert(addcontacts);//4n+27
				System.out.println("Contact added successfully!");//1
			}
			
			break;//1
			
		case "2": searchcontact(); //4n^2+38n+301
			break;//1
			
		case "3": //1
			if(LinkListConatact.empty()) {//2
				System.out.println("contact list is empty");//1
				break;//1
			}
			System.out.println("name of contact");//1
			String tmpname = input.nextLine();//1
			
			Contact tmpcontact = searcbyname(tmpname); //3n+15
			
			if (tmpcontact != null) {//1
				delete(tmpcontact); //2n^2+14n+9
				delete_event(tmpcontact.getContactName());//2n^2+12n+16
				System.out.println("contacts deleted");//1
			}
			else //1
				System.out.println("contact doesnt exist");//1
			break;//1
			
		case "4": //1
			addevent();
			break;
			
		case "5": 
			System.out.println("Enter search criteria: ");
			System.out.println("1. contact name");
			System.out.println("2. Event tittle");
			System.out.print("Enter your choice: ");
			int number_choice = 0;
			String choice = input.nextLine();
			if(choice.equals("1") ) {
				System.out.print("Enter the contact name: ");
				number_choice = 1;
			}
			else if(choice.equals("2")) {
				System.out.print("Enter the event title: ");
				number_choice = 2;
			}
			else {
				System.out.println("wrong choice ");
				return;
			}
			String name =input.nextLine();

			print_events(name, number_choice);
			break;
			
		case "6": 
			if(LinkListConatact.empty()) {
				System.out.println("contact list is empty");
				break;
			}
			System.out.print("Enter the first name: ");
			String tmpfirst = input.nextLine();
			
			if(tmpfirst == null) 
				System.out.println("no name was entered");
			
			else
				print_first(tmpfirst);
			
			break;
			
		case "7": print_events_alpha();
			break;
			
		case "8": //print_contacts();
			run = 1;
			break;
			
		default:
			System.out.println("wrong choice");
	}
		}while(run!=1);
	System.out.println("goodbye!");
	}
	

public Contact addcontact( ) {
	System.out.print("enter the contact name: ");//1
	String contname = input.nextLine();//1
	if(searcbyname(contname)!=null) {//
		 System.out.println("there is contact that have same name");
		 return null;
	}
	System.out.print("enter the contact phone number: ");
	String contphone = input.nextLine();
	if(searchbyphone(contphone)!=null) {
		 System.out.println("there is contact that have same phonenumber");
		 return null;
	}
	
	System.out.print("enter the contact email address: ");
	String contemail = input.nextLine();
	System.out.print("enter the contact addresss: ");
	String contaddress = input.nextLine();
	System.out.print("enter the contact birthday: ");
	String contbirth = input.nextLine();
	System.out.print("enter the contact notes for contact: ");
	String contnote = input.nextLine();
	
	Contact c=new Contact(contname,contphone,contemail,contaddress,contbirth,contnote);
	return c;
}

public Contact searcbyname(String Searchdata) {//big o(n) total =3n+14 changed searchbyname to return Contact and to receive a string // big o(n) total = 2n+8
	if(LinkListConatact.empty()) {//2
		return null;//1
	}
	LinkListConatact.findfirst();//1	
	while(!LinkListConatact.last()) {//n+2
		if((LinkListConatact.retreive()).getContactName().equalsIgnoreCase(Searchdata))//n+2
			return LinkListConatact.retreive();//1
		LinkListConatact.findnext();//n+1
		}
	
	if(LinkListConatact.retreive().getContactName().equalsIgnoreCase(Searchdata)) //2 for last case //1
		return LinkListConatact.retreive();//1
	
	return null;//1
}


public Contact searchbyphone(String Searchdata) { // big o(n) total =3n+8 changed searchbyphone to return Contact and to receive a int 
	if(LinkListConatact.empty()) {//1
		return null;//1
	}
	LinkListConatact.findfirst();//1
	while(!LinkListConatact.last()) {//n+1
		if((LinkListConatact.retreive()).getPhoneNumber().equals(Searchdata) )//n
			return LinkListConatact.retreive();//1
		LinkListConatact.findnext();//n
		}
	
	if((LinkListConatact.retreive()).getPhoneNumber().equals(Searchdata) ) // for last case //1
		return LinkListConatact.retreive();//1
	
	return null;//1
}

public linkedlist<Contact> searchbyeamil(String Searchdata) { // big o(n) total =8n+65   changed search by email to return a list
	if(LinkListConatact.empty()) {//2
		return null;//1
	}
	linkedlist<Contact> emaillist = new linkedlist<>(); //1
	LinkListConatact.findfirst();//1
	
	while(!LinkListConatact.last()) {//n+1
		if(LinkListConatact.retreive().getEmailAddress().equalsIgnoreCase(Searchdata)) {//n+2
			emaillist.Insert(LinkListConatact.retreive()); //4n+27
		}
		
			LinkListConatact.findnext();//n
			}
	
	if((LinkListConatact.retreive()).getEmailAddress().equalsIgnoreCase(Searchdata)) { //3  for last case
		emaillist.Insert(LinkListConatact.retreive());//4n+27
	}
	
	return emaillist;//1
}


public linkedlist<Contact> searchbyAdress(String Searchdata) {//11n+70
	if(LinkListConatact.empty()) {//2
		return null;//1
	}
	linkedlist<Contact> Adresslist = new linkedlist<>();//1 
	LinkListConatact.findfirst();//1

	
	while(!LinkListConatact.last()) {//n+2
		if((LinkListConatact.retreive()).getAddress().equalsIgnoreCase(Searchdata)) {//n+2
			Adresslist.Insert(LinkListConatact.retreive());//4n+28
		}
		
			LinkListConatact.findnext();//n+1
			}
	if((LinkListConatact.retreive()).getAddress().equalsIgnoreCase(Searchdata)) {//3
		Adresslist.Insert(LinkListConatact.retreive());//4n+28
	}
	return Adresslist;//1
}

public linkedlist<Contact> searchbyBirthday(String Searchdata) {//big o(n^2) total =4n^2+7n+67
	if(LinkListConatact.empty()) {//2
		return null;//1
	}
	linkedlist<Contact> Birthlist = new linkedlist<>();//1 
	LinkListConatact.findfirst();//1

	
	while(!LinkListConatact.last()) {//n+2
		if((LinkListConatact.retreive()).getBirthday().equalsIgnoreCase(Searchdata)) {//n+2
			Birthlist.Insert(LinkListConatact.retreive());//4n^2+27
		}
		
			LinkListConatact.findnext();//n
			}
	
	if((LinkListConatact.retreive()).getBirthday().equalsIgnoreCase(Searchdata)) {//3
		Birthlist.Insert(LinkListConatact.retreive());//4n+27
	}
	return Birthlist;//1
}

public void delete(Contact contact_to_delete) { //big o(n^2) total =2n^2+14n+9
	LinkListConatact.findfirst();//1
	
	while(!LinkListConatact.last()) {//n+2
		if(LinkListConatact.retreive().getContactName().equalsIgnoreCase(contact_to_delete.getContactName())) {//n+2
			LinkListConatact.remove();//2n^2+10n   "" (remove = 2n+10 )*while =n ""
		}
		else//n
			LinkListConatact.findnext();//n
	}
	
	if(LinkListConatact.retreive().getContactName().equalsIgnoreCase(contact_to_delete.getContactName())) {//3
		LinkListConatact.remove();//1
	}
		
}

public void delete_event(String contact_to_delete) {//2n^2+12n+16
	if(LinkListEvent.empty()) { //2 no events to delete
		return;//1
	}
	
	LinkListEvent.findfirst();//1
	
	while(!LinkListEvent.last()) {//n+2 // events to delete
		if(LinkListEvent.retreive().getContactinvolved().getContactName().equals(contact_to_delete)) {//n+4
			LinkListEvent.remove();//2n^2+10n
		}
		else//1
			LinkListEvent.findnext();//1
	}
	if(LinkListEvent.retreive().getContactinvolved().getContactName().equals(contact_to_delete))//3
		LinkListEvent.remove();//1
}


public void addevent() {
	System.out.print("enter event title: ");//1
	String title = 	input.nextLine();//1
	
	if(searchevent(title)) {//
		System.out.println("Event title exists: ");
		return;
	}
	
	else {
		System.out.print("Enter contact's name: ");
		String cname = 	input.nextLine();
		Contact cc = searcbyname(cname);
		if(cc == null) {
			System.out.println("no contact found with this name");
			return;
		}
		else {
			System.out.print("Enter event date and time (MM/DD/YYYY HH:MM): ");
			String date_time = 	input.nextLine();
			if(searchdate_time(date_time)) {
				System.out.println("date and time title exists");
				return ;
			}
			
			System.out.print("Enter event location: ");
			String location = 	input.nextLine();
			Event e= new Event(title,date_time,location,searcbyname(cname));
			LinkListEvent.Insert(e);
			System.out.println("Event scheduled successfully! ");
		}


	}
	
}


public boolean searchevent(String title) {
	
		if(LinkListEvent.empty()) {
			return false;
		}
		
		LinkListEvent.findfirst();
		
		while(!LinkListEvent.last()) {
			if((LinkListEvent.retreive()).getTitle().equalsIgnoreCase(title))
				return true;
			LinkListEvent.findnext();
			}
		
		if(LinkListEvent.retreive().getTitle().equalsIgnoreCase(title)) // for last case
			return true;
		
		return false;
	
}

public boolean searchdate_time(String Date_Time) {

if(LinkListEvent.empty()) {
	return false;
}

LinkListEvent.findfirst();

while(!LinkListEvent.last()) {
	if((LinkListEvent.retreive()).getDate_Time().equalsIgnoreCase(Date_Time))
		return true;
	LinkListEvent.findnext();
	}

if(LinkListEvent.retreive().getDate_Time().equalsIgnoreCase(Date_Time)) // for last case
	return true;

return false;

}

public void print_events(String t, int x) {
	int found = 0;

	if(LinkListEvent.empty()) {
		System.out.print("event is empty");
		return;
	}
	if(x == 1) {
		LinkListEvent.findfirst();
		while(!LinkListEvent.last()) {
			if(LinkListEvent.retreive().getContactinvolved().getContactName().equalsIgnoreCase(t)) {
				System.out.println("Events found!\r\n" + LinkListEvent.retreive().toString());
				found = 1;
			}
			LinkListEvent.findnext();
		}
		
		if(LinkListEvent.retreive().getContactinvolved().getContactName().equalsIgnoreCase(t)) { 
			System.out.println("Events found!\r\n" + LinkListEvent.retreive().toString());
			found = 1;
		}
		if(found == 0)
			System.out.println("no matching event found");
	}
	
	else {
		LinkListEvent.findfirst();
		while(!LinkListEvent.last()) {
			if(LinkListEvent.retreive().getTitle().equalsIgnoreCase(t)) {
				System.out.println("Event found!\r\n" + LinkListEvent.retreive().toString());
				found = 1;
			}
			LinkListEvent.findnext();
		}
		
		if(LinkListEvent.retreive().getTitle().equalsIgnoreCase(t)) {
			System.out.println("Event found!\r\n" + LinkListEvent.retreive().toString());
			found = 1;
		}
		if(found == 0)
			System.out.println("no matching events found");
	}
	
}



public String extractfirst(String fullname) { // this method is to extract each first name form the list of contact
	
	int index = 0;
    char currentChar = fullname.charAt(index);
    String firstname = "";

    while (currentChar != ' ' && index < fullname.length()) {
        firstname += currentChar;
        index++;
        if (index < fullname.length()) {
            currentChar = fullname.charAt(index);
        }
    }

    return firstname;
	
}

public void print_first(String first_name) {
	int found = 0;//1
	
	LinkListConatact.findfirst();//1
	
	while(!LinkListConatact.last()) { //n
		
		if(extractfirst(LinkListConatact.retreive().getContactName()).equalsIgnoreCase(first_name)) { 
			if(found == 0) { // (n-1)
				// this if is used so the print will work only once
				System.out.println("Contacts found!");//1
				found = 1;//1
			}
			System.out.println(LinkListConatact.retreive().toString()); // 3(n-1)
		}
		LinkListConatact.findnext(); //n-1
	}
	
	if(extractfirst(LinkListConatact.retreive().getContactName()).equalsIgnoreCase(first_name)) {
		if(found == 0) { // this if is used so the print will work only once
			System.out.println("Contacts found!");
			found = 1;
		}
		System.out.println(LinkListConatact.retreive().toString());
	}
	
	if(found == 0) { // if found = 0 then there is no matching first name
		System.out.println("Contacts not found!");
	}
}
	
	public void print_events_alpha() {
		
		if(LinkListEvent.empty()) { //1
			System.out.println("no events");//1
			return;//1
		}
		LinkListEvent.findfirst(); //1
		 
			while(!LinkListEvent.last()) { // n times
				System.out.println(LinkListEvent.retreive().toString()); //3(n-1)
				LinkListEvent.findnext(); //n-1
			}
			System.out.println(LinkListEvent.retreive().toString()); // 3
	}
	//total is = 
	
	//public void print_contacts() {
		//if(LinkListConatact.empty()) {
			//System.out.print("no contacts");
			//return;
		//}
		//LinkListConatact.findfirst();

			//while(!LinkListConatact.last()) {
				//System.out.println(LinkListConatact.retreive().toString());
				//LinkListConatact.findnext();
			//}
			//System.out.println(LinkListConatact.retreive().toString());
	//}

}

