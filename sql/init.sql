
INSERT INTO rc.rc_item (name,`no`,url,status,created,updated) VALUES
		('权限管理平台','32edc2301c0f74a8',NULL,1,1628752919779,1628752919779);


INSERT INTO rc.rc_action (pid,itemName,itemId,name,url,isIndex,sort,created,updated) VALUES
		(0,'权限管理平台',1,'用户管理','用户管理',1,100,1628756064058,1628756064058),
		(1,'权限管理平台',1,'用户列表','/user/list',1,99,1628756107347,1628756107347),
		(1,'权限管理平台',1,'角色列表','/role/list',1,98,1628756262568,1628756262568),
		(0,'权限管理平台',1,'权限管理','权限管理',1,95,1628757320289,1628757320289),
		(4,'权限管理平台',1,'项目列表','/item/list',1,94,1628757341408,1628757341408),
		(4,'权限管理平台',1,'权限列表','/action/list',1,93,1628757382793,1628757382793),
		(0,'权限管理平台',1,'配置管理','配置管理',1,90,1628757400416,1628757400416),
		(7,'权限管理平台',1,'配置列表','/config/list',1,89,1628757416905,1628757438897),
		(6,'权限管理平台',1,'新增修改权限页面','/action/addOrUpdate',2,88,1628757711424,1628759511061),
		(6,'权限管理平台',1,'删除权限','/action/delete',2,86,1628757741425,1628757741425);
INSERT INTO rc.rc_action (pid,itemName,itemId,name,url,isIndex,sort,created,updated) VALUES
		(6,'权限管理平台',1,'角色增加权限列表','/action/addActionToRoleList',2,84,1628758495970,1628758495970),
		(6,'权限管理平台',1,'角色管理权限列表','/action/relationActionList',2,83,1628758551380,1628758551380),
		(6,'权限管理平台',1,'批量删除权限','/action/deleteItemAction',2,82,1628758694296,1628758694296),
		(6,'权限管理平台',1,'新增修改权限','/action/doAddOrUpdate',2,84,1628759529989,1628759529989),
		(3,'权限管理平台',1,'添加权限','/role/doAddAction',2,86,1628760995783,1628760995783),
		(3,'权限管理平台',1,'去除权限','/role/deleteRelationAction',2,82,1628761029812,1628761029812),
		(0,'权限管理平台',1,'首页','/index',2,0,1628823707771,1628823707771),
		(6,'权限管理平台',1,'获取权限树','/item/treeActionBack',2,80,1628824833488,1628824833488),
		(3,'权限管理平台',1,'新增修改角色页面','/role/addOrUpdate',2,79,1628825215724,1628825215724),
		(3,'权限管理平台',1,'新增修改角色','/role/doAddOrUpdate',2,78,1628825247852,1628825247852);
INSERT INTO rc.rc_action (pid,itemName,itemId,name,url,isIndex,sort,created,updated) VALUES
		(3,'权限管理平台',1,'删除角色','/role/delete',2,77,1628825282717,1628825282717),
		(3,'权限管理平台',1,'为角色加权限','/role/doAddAction',2,77,1628825446309,1628825446309),
		(3,'权限管理平台',1,'删除角色权限','/role/deleteRelationAction',2,76,1628825515413,1628825515413),
		(3,'权限管理平台',1,'用户关联角色权限','/role/relationRoleList',2,75,1628825711253,1628825711253),
		(3,'权限管理平台',1,'为用户添加角色列表页面','/role/addRoleToUserList',2,74,1628825743995,1628825743995),
		(5,'权限管理平台',1,'新增修改项目页面','/item/addOrUpdate',2,69,1628825944515,1628825944515),
		(5,'权限管理平台',1,'新增修改项目','/item/doAddOrUpdate',2,68,1628825983268,1628825983268),
		(5,'权限管理平台',1,'项目关联权限列表','/item/relationActionList',2,67,1628826066780,1628826066780),
		(5,'权限管理平台',1,'删除项目','/item/delete',2,65,1628826346396,1628826346396),
		(7,'权限管理平台',1,'新增配置页面','/config/add',2,59,1628826682708,1628826682708);
INSERT INTO rc.rc_action (pid,itemName,itemId,name,url,isIndex,sort,created,updated) VALUES
		(7,'权限管理平台',1,'新增配置','/config/doAdd',2,58,1628826704532,1628826704532),
		(7,'权限管理平台',1,'修改权限页面','/config/edit',2,57,1628826728786,1628826728786),
		(7,'权限管理平台',1,'修改配置','/config/doEdit',2,55,1628826748714,1628826748714),
		(7,'权限管理平台',1,'删除权限','/config/remove',2,54,1628826785937,1628826785937),
		(2,'权限管理平台',1,'新增修改用户页面','/user/addOrUpdate',2,49,1628826938916,1628826938916),
		(2,'权限管理平台',1,'新增修改用户','/user/doAddOrUpdate',2,58,1628826969898,1628826969898),
		(2,'权限管理平台',1,'为用户添加角色','/user/doAddRole',2,57,1628827028026,1628827028026),
		(2,'权限管理平台',1,'删除用户关联角色','/user/deleteRelationRole',2,56,1628827054378,1628827054378),
		(2,'权限管理平台',1,'删除用户','/user/delete',2,55,1628827076842,1628827076842);

INSERT INTO rc.rc_action (pid,itemName,itemId,name,url,isIndex,sort,created,updated) VALUES
		(7,'权限管理平台',1,'判断配置是否存在','/config/checkKeyword',2,55,1629168186094,1629168186094);


INSERT INTO rc.rc_role (name,note,created,updated) VALUES
		('超级管理员','拥有所有权限',1628827076842,1628827076842);


INSERT INTO rc.rc_role_action (roleId,actionId,created,updated) VALUES
		(1,1,NULL,NULL),
		(1,2,NULL,NULL),
		(1,3,NULL,NULL),
		(1,4,NULL,NULL),
		(1,5,NULL,NULL),
		(1,6,NULL,NULL),
		(1,7,NULL,NULL),
		(1,8,NULL,NULL),
		(1,9,NULL,NULL),
		(1,15,NULL,NULL);
INSERT INTO rc.rc_role_action (roleId,actionId,created,updated) VALUES
		(1,10,NULL,NULL),
		(1,14,NULL,NULL),
		(1,11,NULL,NULL),
		(1,12,NULL,NULL),
		(1,16,NULL,NULL),
		(1,13,NULL,NULL),
		(1,17,NULL,NULL),
		(1,18,NULL,NULL),
		(1,19,NULL,NULL),
		(1,20,NULL,NULL);
INSERT INTO rc.rc_role_action (roleId,actionId,created,updated) VALUES
		(1,22,NULL,NULL),
		(1,21,NULL,NULL),
		(1,23,NULL,NULL),
		(1,24,NULL,NULL),
		(1,25,NULL,NULL),
		(1,26,NULL,NULL),
		(1,27,NULL,NULL),
		(1,28,NULL,NULL),
		(1,29,NULL,NULL),
		(1,30,NULL,NULL);
INSERT INTO rc.rc_role_action (roleId,actionId,created,updated) VALUES
		(1,36,NULL,NULL),
		(1,31,NULL,NULL),
		(1,37,NULL,NULL),
		(1,32,NULL,NULL),
		(1,38,NULL,NULL),
		(1,39,NULL,NULL),
		(1,33,NULL,NULL),
		(1,34,NULL,NULL),
		(1,35,NULL,NULL),
		(1,40,NULL,NULL);


INSERT INTO rc.rc_user (company,job_number,nickname,cardNum,email,status,created,updated,password) VALUES
		('才华','0001','jack','001',NULL,1,NULL,NULL,'49ba59abbe56e057');

INSERT INTO rc.rc_user_role (userId,roleId,created,updated) VALUES
		(1,1,NULL,NULL);