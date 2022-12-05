견고한 아키텍처를 만들어보자
# architecture-practice

```
src/main
├──  resources
│    └── application.yml
└──  java/com/example/demo/architecture
     ├── Application.class
     ├── global
     │   ├── common
     │   │   ├── Mapper.class
     │   │   ├── PersistenceAdapter.class
     │   │   ├── SelfValidating.class
     │   │   ├── UseCase.class
     │   │   └── WebAdapter.class
     │   ├── database
     │   │   ├── InitDb.class
     │   │   └── InitService.class
     │   └── exception
     │       └── NotEnoughStockException.class
     └── order
         ├── adapter
         │   ├── in
         │   │   └── web
         │   │       ├── OrderController.class
         │   │       ├── dto
         │   │       │   ├── CancelOrderCommand.class
         │   │       │   └── OrderResponseDto.class
         │   │       └── mapper
         │   │           └── InputOrderMapper.class
         │   └── out
         │       └── persistence
         │           ├── mapper
         │           │   └── OutputOrderMapper.class
         │           └── order
         │               ├── OrderJpaEntity.class
         │               ├── OrderJpaRepository.class
         │               └── OrderPersistenceAdapter.class
         ├── application
         │   ├── port
         │   │   ├── in
         │   │   │   └── CancelOrderUseCase.class
         │   │   └── out
         │   │       ├── LoadOrderPort.class
         │   │       ├── OrderLock.class
         │   │       └── UpdateOrderStatusPort.class
         │   └── service
         │       ├── CancelOrderService.class
         │       ├── LoadOrderService.class
         │       └── NoOpOrderLock.class
         └── domain
             └── order
                 ├── Order.class
                 └── OrderStatus.class
```
