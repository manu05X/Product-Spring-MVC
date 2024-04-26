#### Here we are having a USER for a Ed-tech web-site.
 - USER
    - Studen
    - Instructor
    - Mentor
    - TA's
 

- But from Ed-tech web-site POV we will never have a simple USER in any point of time.
- Their alwase going to be a user in form of Student, Instructor, Mentor, TA's.
- Their for we will never need a user table in our DB as we will not creating USER as a object anywhere in our code.
- So, make USER as a Abstract class.
- This is done by @MappedSuperclass.
- @MappedSuperclass -> It will not create the table of the user it will just create the table of its child where it has been mapped and put all its attribut to the column of it's child