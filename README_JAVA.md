三层架构
------------------------------
* Controller：控制层，接收前端发起的请求，对请求进行处理，并响应数据。
* Service：业务逻辑层，处理具体的业务逻辑。
* Dao:数据访问层（持久层），负责数据访问操作，包括数据的增删改查

注解
-----------------------------
* Component (IOC容器管理Bean)
    * Controller
    * Service
    * Repository(Dao)
* RestController
    * Controller
    * ResponseBody
    
同类型的Bean存在多个
------------------------------
* Primary   (可以设置注入的优先级)
* Autowired + Qualifier("empServiceB")   (可以指定注入的Bean类，value是Bean名)
* Resource(name = "empServiceB)

MyBatis来简化JDBC的开发
------------------------------
* Mapper注解

lombok 自动生成构造器
-------------------------------
* @Getter/Setter
* @ToString
* @EqualsAndHashCode
* @Data = (@Getter + @Setter + @ToString + @EqualsAndHashCode)
* @NoArgsConstructor (无参构造)
* @AllArgsConstructor (全参构造)
    