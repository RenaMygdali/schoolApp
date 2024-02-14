package gr.aueb.cf.schoolApp;

import java.awt.EventQueue;

public class Main {
	
	private static Menu menu; 
	private static SearchForm teachersSearchForm;
	private static UpdateDeleteForm teachersUpdateDeleteForm;
	private static InsertForm teachersInsertForm;
	private static LoginPage loginPage;
	private static SearchUserForm searchUserForm;
	private static InsertUserForm insertUserForm;
	private static UpdateDeleteUserForm updateDeleteUserForm;
	
	
	public static void main(String[] args) {
				
		/* It's the queue inside which all events are queued.*/
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					loginPage = new LoginPage();
					loginPage.setVisible(true);
					
					menu = new Menu();
					menu.setVisible(false);
					
					teachersSearchForm = new SearchForm();
					teachersSearchForm.setVisible(false);
					
					teachersUpdateDeleteForm = new UpdateDeleteForm();
					teachersUpdateDeleteForm.setVisible(false);
					
					teachersInsertForm = new InsertForm();
					teachersInsertForm.setVisible(false);
					
					
					searchUserForm = new SearchUserForm();
					searchUserForm.setVisible(false);
					
					insertUserForm = new InsertUserForm();
					insertUserForm.setVisible(false);
					
					updateDeleteUserForm = new UpdateDeleteUserForm();
					updateDeleteUserForm.setVisible(false);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public static Menu getMenu() {
		return menu;
	}


	public static SearchForm getTeachersSearchForm() {
		return teachersSearchForm;
	}


	public static UpdateDeleteForm getTeachersUpdateDeleteForm() {
		return teachersUpdateDeleteForm;
	}


	public static InsertForm getTeachersInsertForm() {
		return teachersInsertForm;
	}
	
	public static LoginPage getLoginPage() {
		return loginPage;
	}
	
	public static SearchUserForm getSearchUserForm() {
		return searchUserForm;
	}
	
	public static InsertUserForm getInsertUserForm() {
		return insertUserForm;
	}
	
	public static UpdateDeleteUserForm getUpdateDeleteUserForm() {
		return updateDeleteUserForm;
	}
}
