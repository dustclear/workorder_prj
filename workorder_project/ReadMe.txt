注意
1、generatorConfig.xml文件中的classPathEntry元素，选择你自己数据库驱动的位置。
<classPathEntry location="c:/ojdbc14.jar" />

2、generatorConfig.xml文件中的table标签，选择你自己数据库的表名
<table schema="hr" tableName="countries" domainObjectName="Customer">