# Cadastro de Veículos OO

Sistema de console em Java (POO) para cadastrar, listar e consultar veículos.
Os dados ficam apenas em memória (`List<Veiculo>`) e são perdidos ao encerrar o programa.

## Integrantes
- Arthur Saggin
- João Isaque
- Nycolas Campos
- Lucas Varal

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