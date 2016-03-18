Training: computer-database
======================================


##1 Database: done.

##2 IDE: done.

##3 Git repository: done.

##4 Start coding:
###4.1 Layout:
	- Check the constraints/privileges of DB.
###4.2 Command line interface client:
####	- 4.2.1 Start:
	- Organization of the packages.
	- Models for Companies and Computers.
	- Singleton Connection to DB with JDBC.
	- DAOs (interface, computer and company).
	- Mapping (interface, computer and company).
	- Exceptions.
	- Testing (for DAOs).
	- Controllers for computer and company.
	- UI : Done.
####	- 4.2.2 Pages:
	- Pagination : Done.
####	- 4.2.3 Code Review:
	- Refactoring done.
###4.3 CLI + Web Interface Client:
####	- 4.3.1 Maven, Logging & Unit testing:
	- Maven standard: done.
	- Unit testing : DBUnit + Unit tests (done for model and DAO, need to expand it).
	- Fail : objects are not mocked.
	- Logging : done for some classes, need to expand it everywhere.
####	- Implement listing and computer add features in the web-ui.
	- Added DTOs. Need to rework the CLI to match the new back.
	- Refactored Service to match a singleton and a universal service (for CLI and Web-UI...)
	- Views in JSP (using JSTL) are done, used tags for links and for pagination.
	- Testing in Selenium : todo.
####	- 4.3.3 Secure through validation
	- jQuery has been implemented. Gotta check if that's enough.
	- Backend testing is very very low. Need to create a new Validation class which raises a specific exception if not matched.
	