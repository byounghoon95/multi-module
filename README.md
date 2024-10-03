# Multi Module

### Multi Module 구조
만들고자 하는 Multi Module 은 아래와 같다. <br>
core에 공통 파일을 넣고 multi-module-api 에서 이를 사용할 것이다.

```text
multi-module/              
├── core/                   
│   └── build.gradle        
├── multi-module-api/       
│   └── build.gradle        
├── settings.gradle         
└── build.gradle            
```

### Multi Module 세팅

#### 1. settings.gradle 설정
multi-module 하위의 settings.gradle을 설정한다. include 에 서브 프로젝트를 넣으면 
root 프로젝트의 하위에 해당 프로젝트를 모듈로 포함한다는 의미이다.
```text
rootProject.name = 'multi-module'
include 'core'
include 'multi-module-api'
```

#### 2. build.gradle 설정
multi-module 하위의 build.gradle을 설정한다. 
```text
plugins {
	id 'java'
	id 'org.springframework.boot' version '3.3.4'
	id 'io.spring.dependency-management' version '1.1.6'
}

bootJar.enabled = false

allprojects{
	group = 'com.example'
	version = '0.0.1-SNAPSHOT'
	sourceCompatibility = '17'
    
	repositories {
		mavenCentral()
	}
}

subprojects {
	group = 'com.example'
	version = '0.0.1-SNAPSHOT'

	apply plugin: 'java-library'
	apply plugin: 'org.springframework.boot'
	apply plugin: 'io.spring.dependency-management'

	// 관리하는 모듈의 공통 dependency
	dependencies {
		implementation 'org.springframework.boot:spring-boot-starter'
		implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
		implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0'
		compileOnly 'org.projectlombok:lombok'
		annotationProcessor 'org.projectlombok:lombok'
		testImplementation 'org.springframework.boot:spring-boot-starter-test'
		testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
	}

	configurations {
		compileOnly {
			extendsFrom annotationProcessor
		}
	}

	tasks.named('test') {
		useJUnitPlatform()
	}
}

project(":multi-module-api") {
	dependencies {
		implementation project(":core")
	}
}

project(':core') {
	bootJar { enabled = false }
	jar { enabled = true }
}
```
* bootJar.enabled = false
  * 루트 프로젝트는 실행가능한 jar 로 생성될 필요가 없어 비활성화
* allprojects
  * 모든 루트 및 서브 플로젝트에 공통적으로 적용
* subprojects
  * 서브 프로젝트에 적용
* project(":multi-module-api")
  * multi-module-api 가 core 프로젝트에 의존하도록 설정
* project(':core')
  * core 프로젝트는 다른 프로젝트에서 사용되는 라이브러리로 직접 실행할 필요가 없다.
    하지만 의존성으로 사용되기에 일반 jar 파일은 필요하기에 bootJar 는 비활성화한다.

#### 3. build.gradle 설정
multi-modul-api 하위의 build.gradle 에 대한 설정이다. core 프로젝트 impl을 통해
core에 있는 파일들을 사용할 수 있다
```text
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-web'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
	implementation project(':core')
}
```

### Reference
* https://dkswnkk.tistory.com/691
* https://studyandwrite.tistory.com/539
