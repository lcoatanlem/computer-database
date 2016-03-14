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
	- Models for Companies and Computers, documented.
	- Singleton Connection to DB with JDBC, documented.
	- DAOs (interface, computer and company), documented.
	- Mapping (interface, computer and company), documented.
	- Exceptions, documented.
	- Testing (for DAOs), documented.
	- Controllers for computer and company, documented.
	- UI : Done.
	- Logs : TODO.
####	- 4.2.2 Pages:
	- Pagination : Done.
####	- 4.2.3 Code Review:
	- Refactoring in progress (added DBUnit, changed connection singleton to instance singleton,
									changed to put connection informations in a properties file,
									only one connection for main and test use, reduced number of entries in
									test database) ...