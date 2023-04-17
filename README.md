Вычислитель отличий – программа, определяющая разницу между двумя структурами данных. 
Это популярная задача, для решения которой существует множество онлайн-сервисов, например: http://www.jsondiff.com/. 
Подобный механизм используется при выводе тестов или при автоматическом отслеживании изменении в конфигурационных файлах.

Возможности утилиты:

Поддержка разных входных форматов: yaml и json
Генерация отчета в виде plain text, stylish и json


### Hexlet tests and linter status:
[![Actions Status](https://github.com/NataliVod/java-project-71/workflows/hexlet-check/badge.svg)](https://github.com/NataliVod/java-project-71/actions)
[![Java CI](https://github.com/NataliVod/java-project-71/actions/workflows/main.yml/badge.svg)](https://github.com/NataliVod/java-project-71/actions/workflows/main.yml)
[![Maintainability](https://api.codeclimate.com/v1/badges/00d8e259a48677f10816/maintainability)](https://codeclimate.com/github/NataliVod/java-project-71/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/00d8e259a48677f10816/test_coverage)](https://codeclimate.com/github/NataliVod/java-project-71/test_coverage)
### asciinema records

[Сравнение плоских файлов (JSON)](https://asciinema.org/a/knlRK21rLV3114mfgHK65jEVh) 

[Сравнение плоских файлов (yaml)](https://asciinema.org/a/xoVxAxMJ2xpemTI2XKtZdidzE)

[Сравнение вложенных структур](https://asciinema.org/a/95aNnErJCZbw3KW4yxhEVbtTO) 

[Плоский формат](https://asciinema.org/a/G7qsIwoKktsilaPRjJ47vzUql) 

[Вывод в json](https://asciinema.org/a/sWao3FFWSRSrhdmvNSKqoxgho) 

## Setup
```sh
make build
```

## Run
```sh
make run
```

## Run checkstyle
```sh
make lint
```

## Run test
```sh
make test
```

## Run clean
```sh
make clean
```

## Run test report
```sh
make report
```

## Run update dependencies
```sh
make update-deps
```
