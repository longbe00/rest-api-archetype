## Synopsis
rest API 프로젝트를 구성하기 위한 최소한의 구성으로 이루어진 arche-type 프로젝트
초기 프로젝트 셋팅을 위한 불필효한 resource 낭비를 최소화 하기 위해서 구축 
 
구성사항:
 - jdbc
 - mybatis(mapper)
 - web context
 - http logging/cors/murtipart/CharacterEncoding filter
 - exception
 - swagger 


## Nexus archetype 추가 

배포할 자신의 nexus 서버를 설정하여 arch-type 프로젝트를 생성할 수 있다. 
```
    <distributionManagement>
        <repository>
            <id>nexus</id>
            <name>RepositoryProxy</name>
            <url></url>
        </repository>
        <snapshotRepository>
            <id>nexus</id>
            <name>RepositoryProxy</name>
            <url></url>
        </snapshotRepository>
    </distributionManagement>
```
```
mvn install
```
 ## Building & Install
```
mvn archetype:generate                                  \
  -DarchetypeGroupId=com.mikefree
  -DarchetypeArtifactId=rest-api-archetype            \
  -DarchetypeVersion=1.0.0                \
  -DgroupId=<my.groupid>                                \
  -DartifactId=<my-artifactId>
 ```