# qs-practice

## 公司架构模拟
## 层级介绍：

#### base:基础数据访问架构封装

#### core:dao层操作（mybatis实现）

#### cache:缓存整合SpringDateRedis(目前单节点)

#### service:业务逻辑模块

#### web:控制层模块

#### generator:主要对dao层代码生成工具,基于springboot（ 参考地址：https://gitee.com/babaio/renren-generator.git ），对其进行了针对本项目做针对的模板修改

#### java:计划针对java不同版本的特性按点划分，组织梳理

#### design-patterns:设计模式（《大化设计模式》代码练习）

#### algorithm:基础数据结构/基础算法梳理整合


* :spider_web: 数据库：mysql+druid连接池

* :artificial_satellite: 缓存：redis

* :earth_asia: mvc框架：spring+springmvc+mybatis

* :bookmark_tabs: 作业调度：quartz

* :globe_with_meridians: 页面相关：bootstrap+echarts+freemarker

* :factory: 代码生成：springboot+mybatis+velocity

* :rofl: 项目特点：基于mybatis对数据访问层的原子操作（单表，可选带条件查询，统计）一个薄层封装，目前稳定服务于公司业务，
    对后台接口编写起到了很大的帮助作用，基本上单表操作不需要再写sql，对于业务中的复杂数据访问（联表聚合查询可以封装额外的xml的映射文件）,
    整合了quartz，登录校验目前使用过滤器基于token方式实现
