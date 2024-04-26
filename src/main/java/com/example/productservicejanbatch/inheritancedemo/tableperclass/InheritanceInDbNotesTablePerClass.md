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

### Now if we need simple USER then what ti do?
- Here we will create table per class (tpc_name) and provide a custom name to the Entity.
- Table per class means - The DB will create table for all entites with columns inherited from Parent.
  - That is DB is trying to make each and every table independant and isolated 
- For the user class. 
  - @Entity(name = "tpc_user") //as we already have user defined before in mappedsuper module, so we gave a custom name to it.
  - @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
- For mentor class.
  - @Entity(name = "tpc_mentor")
