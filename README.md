greenDAO
========
greenDAO is a light & fast ORM solution for Android that maps objects to SQLite databases. Being highly optimized for Android, greenDAO offers great performance and consumes minimal memory.

Home page, documentation, and support links: http://greendao-orm.com/

Release History
---------------
### Next version (unreleased): Minor features
* Limited support of String PKs
* Added CountQuery for creating SELECT COUNT (*) queries with QueryBuilder
* Added getDatabase in DaoMaster, DaoSession, and Dao
* Added insertOrReplaceInTx in Dao
* Made DAOs and DaoSessions in active entities transient
* Minor fixes

### V1.1.2 (2012-03-26): ADT 17 support for demo project
* Demo projects works with ADT 17 (moved greendao.jar into libs)
* CREATE/DROP TABLE may be skipped for entity types: This allows having multiple entity tapes operate on one table
* Minor improvements

### V1.1.1 (2012-02-14): Mini bugfix&feature release
* Added NOT IN condition for QueryBuilder
* Fix for Roboelectric (Issue #22)
* Minor fix (Issue #5)

### V1.1.0 (2012-02-13): Feature release
* DeleteQuery for bulk deletes
* Entities may implement Java interfaces
* Entities may extend a Java class
* Added LIMIT and OFFSET support for QueryBuilder and Query
* Convenience methods to add named relationships
* SQL scripts are executed in a transaction by default
* Fixed queries with special column names (SQL keywords)
* Changed default names for to-many relations to end with "List"
* ORDER BY uses LOCALIZED collation for strings by default

### V1.0.1 (2011-10-30): Bugfix release
* Fixed generation of to-many relations
* Fixed generated import statements when entities/DAO are not in the same package

### V1.0.0 (2011-10-24): First open source release
* To-many relations (lazily loaded on the entities)
* To-many relations with custom join properties
* Active entities can be updated, refreshed, and deleted directly
* Significant performance improvements (faster identity scope, faster database result parser)
* "Keep sections" for custom code in entities were added that won't be overwritten during code generation
* Other minor improvements

### Third preview (2011-08-19) 
http://greendao-orm.com/2011/08/19/query-builder-and-lazy-loading-lists/

### Second preview (2011-08-12) 
http://greendao-orm.com/2011/08/12/greendao-2nd-preview/

### First public release (2011-08-04) 
http://greendao-orm.com/2011/08/04/greendao-public-release/