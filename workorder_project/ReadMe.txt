1. 修改applicationContext-common.xml中的数据库连接信息：url, 用户名，密码；
2. 启动tomcat，用以下地址访问webservice服务：
	http://localhost:8080/workorder_project/ws/InstallDocument?wsdl
3. 调用webservice接口需要传人的参数及返回值数据结构可以参照test文件夹下的TestInstallDocMgt.java。（json 格式数据的key应该和pojo下的类属性名称或数据库字段名称一致。）