# Controller
    The need for Controller is to do routing of request to appropriate methods and respond to requests.
# DTO
    # lombok
# Service
    ### Service
            *    Forwarder of Requests
            *    RestTemplate
            *    ResponseEntity
            *    RequestEntity
            *    Marking Service class as primary
    ### Client Service
    #### Adapter Pattern 

# Exception Handling
        ## Controller Advice
        ## Custom Exception Handlers


# DB Connections

## Flow :
* Data Flow

        * SpringJPA -> JPA -> ORM (Hibernate) -> JDBC -> SQL.
        *  We could have created our own SQL queries and hit the DB - but this is tedious effort and  we like to have other solutions ,where we get ORM for help
             * ORM - Object Relationship Mapping.

# Representing Inheritance in Entity.

  *  Mapped Super Class
            
            **  Seperate Entity will not be Created (usually used for parents/base - which should be created in every table)
            **  This is like a Abstract Class, which is used to create extended entity
            ** Child classes will have properties of itself and its parents
  *   Table Per Class
          
            **   Similar to Mapped Super Class , but there will be one table for one entity.(Parent class also have created a table)  
            ** Child Tables will have all attributes of parent class.
            **  If we do parentRepository.findAll()
                 Will give  all records with parent and its child mappings
  *   Joined Table.
                
                ** It creates table same as "Table Per Class" so there will be table for Parent and each child.
                ** All of Attributes of parent are only present in parent class
                ** parent attributes are defined as foreign key in child table. - so you need to do a "join"  if you need to fetch all properties
  *  Single Table
                
                **  Will create only one giant table
                **  Table created will have columns of parent class and all of its child tables as well.
                ** It is fast , but not all records are filled for each record ,hence wastage of storage happens.
