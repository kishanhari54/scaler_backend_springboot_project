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

# Representing Inheritance in Entity.(DB)

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

# Cardinality Mappings in Databases
  * Different Cardinality
  * 
    1. One  : One
    2. One  : Many
    3. Many : One
    4. Many : Many

    *   Example  :     1   : 1
                    Product:Category
                       M   : 1
    *   To Define Same relation between two different Entity, We need to pass (mappedBy) as input to OneToMany Annotation
    *   To Fetch only Parent entity and Child Entity :use @OneToMany(fetchType=EAGER) with child entity, by default its "lazy"
    *   Whenever we create Cardinality, we could use JOIN_COLUMN to define Foreign Key Constraints (name ,mapping table for many to many etc..).


---
# Cascading
  *   Whenever we delete a item , its related items should delete , similarly when we create a object and save it , it needs to save child tables as well first right , hence we define cascaseType as persist.

# Repository
  *   Client Request <--> Controller <--> Services <--> Repository <--> Models.
  *   Repository is a Class which allows CRUD on DB.
  *   The Class which interact with DB Models.
    ## Spring Data JPA
    * Spring Data JPA removes hard work to create SQL Queries ,by creating itself.   