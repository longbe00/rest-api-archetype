## Synopsis
rest API 프로젝트를 구성하기 위한 최소한의 구성으로 이루어진 arche-type 프로젝트
구성사항:
 - jdbc
 - mybatis(mapper)
 - web context
 - http logging/cors/murtipart/CharacterEncoding filter
 - 


## POM 설정
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

## Motivation

A short description of the motivation behind the creation and maintenance of the project. This should explain **why** the project exists.

## Installation

Provide code examples and explanations of how to get the project.

## API Reference

Depending on the size of the project, if it is small and simple enough the reference docs can be added to the README. For medium size to larger projects it is important to at least provide a link to where the API reference docs live.

## Tests

Describe and show how to run the tests with code examples.

## Contributors

Let people know how they can dive into the project, include important links to things like issue trackers, irc, twitter accounts if applicable.

## License

A short snippet describing the license (MIT, Apache, etc.)
