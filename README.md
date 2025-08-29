Data flow in DDD with Graphql:

1. Resolver with mapping
2. UseCase with business logic
3. Repository implemented in RepositoryAdapter for mapping between entity and domain object
4. Jpa Repository to access DB

Struktura: 
```
user/
├── domain/                     # czyste reguły biznesu i model
│     ├── User.java             # encja / agregat root
│     ├── UserRepository.java   # interfejs repozytorium
│     └── UserMapper.java       # mapowanie model <-> entity (jak chcesz oddzielić)
│
├── application/           # przypadki użycia (logika aplikacji)
│     └── RegisterUserUseCase.java  
│
├── infrastructure/                 # implementacje detali technicznych
│     ├── UserEntity.java           # JPA entity
│     ├── JpaUserRepository.java    # implementacja UserRepository (extends JpaRepository)
│     └── UserRepositoryAdapter.java  # adapter mapujący domain repo na JPA repo
│
└── presentation/          # GraphQL i kontrolery
      └── UserResolver.java
```