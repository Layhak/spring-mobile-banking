### Project Structure

```
src
├── main
│   ├── java
│   │   └── co
│   │       └── istad
│   │           └── mobilebankingcstad
│   │               ├── MobileBankingCstadApplication.java
│   │               ├── adiser
│   │               │   └── GlobalRestControllerAdviser.java
│   │               ├── config
│   │               │   ├── SecurityConfiguration.java
│   │               │   └── WebMvcConfiguration.java
│   │               ├── domain
│   │               │   ├── Account.java
│   │               │   ├── AccountType.java
│   │               │   ├── Authority.java
│   │               │   ├── Role.java
│   │               │   ├── User.java
│   │               │   └── UserAccount.java
│   │               ├── features
│   │               │   ├── accounts
│   │               │   │   ├── AccountRepository.java
│   │               │   │   ├── AccountRestController.java
│   │               │   │   ├── AccountService.java
│   │               │   │   ├── AccountServiceImpl.java
│   │               │   │   ├── AuthRestController.java
│   │               │   │   └── dto
│   │               │   │       ├── AccountRequest.java
│   │               │   │       └── AccountResponse.java
│   │               │   ├── accounttype
│   │               │   │   └── AccountTypeRepository.java
│   │               │   ├── authority
│   │               │   │   └── AuthorityRepository.java
│   │               │   ├── files
│   │               │   │   ├── FileRestController.java
│   │               │   │   ├── FileService.java
│   │               │   │   ├── FileServiceImpl.java
│   │               │   │   └── dto
│   │               │   │       └── FileResponse.java
│   │               │   ├── roles
│   │               │   │   └── RoleRepository.java
│   │               │   ├── user
│   │               │   │   ├── UserRepository.java
│   │               │   │   ├── UserRestController.java
│   │               │   │   ├── UserService.java
│   │               │   │   ├── UserServiceImpl.java
│   │               │   │   └── dto
│   │               │   │       ├── UserRequest.java
│   │               │   │       ├── UserResponse.java
│   │               │   │       └── UserUpdateRequest.java
│   │               │   └── useraccount
│   │               │       └── UserAccountRepository.java
│   │               ├── init
│   │               │   └── DataInitializer.java
│   │               ├── mapper
│   │               │   ├── AccountMapper.java
│   │               │   └── UserMapper.java
│   │               ├── security
│   │               │   ├── CustomUserDetail.java
│   │               │   ├── CustomUserDetailService.java
│   │               │   ├── JwtToUserConverter.java
│   │               │   └── KeyUtils.java
│   │               └── utils
│   │                   └── BaseResponse.java
│   └── resources
│       ├── application-dev.yaml
│       ├── application-prod.yaml
│       └── application.yaml
└── test
    └── java
        └── co
            └── istad
                └── mobilebankingcstad
                    └── MobileBankingCstadApplicationTests.java

30 directories, 44 files

```