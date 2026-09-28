# Cadastro de Veículos OO

Sistema de console em Java (POO) para cadastrar, listar e consultar veículos.
Os dados ficam apenas em memória (`List<Veiculo>`) e são perdidos ao encerrar o programa.

## Integrantes
- Arthur Saggin 1139361
- João Isaque 1139559   
- Nycolas Campos 1139527
- Lucas Varal 1136676

## Estrutura
```
src/
├── Veiculo.java            # Entidade (atributos privados + getters)
├── CadastroVeiculos.java   # Lista em memória + regras de validação
└── Main.java               # Menu e interação via console
```
```bash
cd src
javac -encoding UTF-8 *.java
java Main
```