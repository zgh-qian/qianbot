-- 清除无用数据
delete from user where isDelete = 1;
delete from userUsage where isDelete = 1;
delete from app where isDelete = 1;
delete from appQuestion where isDelete = 1;
delete from appOption where isDelete = 1;
delete from appResult where isDelete = 1;
delete from appAnswer where isDelete = 1;
delete from chart where isDelete = 1;