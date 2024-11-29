# Jogo de Tabuleiro Estilo "Banco Imobiliário" com Lista Ligada Circular

## Descrição do Projeto

Este projeto implementa uma simulação de um jogo de tabuleiro no estilo "Banco Imobiliário" utilizando **Java**. A estrutura principal do tabuleiro é representada por uma **lista ligada circular**, onde cada nó é uma casa do tabuleiro. Jogadores são gerenciados com uma estrutura separada, com atributos como saldo bancário, posição no tabuleiro, propriedades possuídas, entre outros.

O jogo envolve movimentação baseada em um dado, compra e venda de imóveis, pagamento de aluguéis e impostos, e recebimento de salário ao passar pela casa "Início". O objetivo final é determinar o jogador com o maior saldo ao final das rodadas ou quando os demais jogadores forem à falência.

## Funcionalidades

### Tabuleiro
- **Estrutura**: Lista ligada circular, onde cada nó representa uma casa do tabuleiro.
- **Tipos de casas**:
  - **Início**: Todos os jogadores começam nesta casa e recebem salário ao passar por ela.
  - **Imóveis**: Podem ser comprados pelos jogadores. Caso já tenha dono, o jogador paga aluguel.
  - **Imposto**: O jogador paga 5% do seu patrimônio ao parar nesta casa.
  - **Restituição**: O jogador recebe 10% de seu salário.
  
### Jogadores
- **Estrutura**: Lista ou array que armazena informações dos jogadores.
- **Atributos**:
  - **Nome**.
  - **Saldo bancário**: Atualizado com salários, compras, aluguéis e outras transações.
  - **Posição no tabuleiro**: Atualizada a cada jogada.
  - **Propriedades possuídas**: Lista de imóveis comprados pelo jogador.

### Regras do Jogo
- **Movimentação**: Cada jogador rola um dado (número aleatório entre 1 e 6) e avança o número correspondente de casas.
- **Interações com casas**:
  - **Imóvel disponível**: O jogador pode comprar o imóvel.
  - **Imóvel já comprado**: O jogador deve pagar aluguel ao proprietário.
  - **Imposto**: O jogador paga 5% de seu patrimônio.
  - **Restituição**: O jogador recebe 10% de seu salário.
  - **Salário**: Sempre que o jogador passar pela casa "Início", recebe o salário.

### Funcionalidades Implementadas

#### Antes do Início do Jogo
- **Cadastro de Imóveis**:
  - Cadastrar até 40 imóveis.
  - Listar, atualizar e remover imóveis.
  - Validação: não permitir o início do jogo com menos de 10 imóveis cadastrados.

- **Cadastro de Jogadores**:
  - Cadastrar até 6 jogadores.
  - Listar, atualizar e remover jogadores.
  - Validação: não permitir o início do jogo com menos de 2 jogadores.

- **Configurações Iniciais**:
  - Definir saldo inicial e salário dos jogadores.
  - Determinar o número máximo de rodadas.

#### Durante o Jogo
- **Movimentação**:
  - Rolar o dado e avançar pelo tabuleiro conforme o valor obtido.

- **Interações**:
  - Realizar transações financeiras (cobrar aluguel, pagar impostos e salários).
  - Exibir feedbacks: 
    - "Saldo insuficiente".
    - "Parabéns pela compra do imóvel X".
    - "Você pagou R$XXX de imposto".

- **Status do Jogo**:
  - Exibir status atualizado dos jogadores: posição, saldo bancário e propriedades.

#### Encerramento do Jogo
- Declarar o vencedor: jogador com maior saldo ao final das rodadas ou quando os outros jogadores falirem.

### Funcionalidades Adicionais
- Implementação de uma **casa de prisão**: mantém o jogador preso por N rodadas.
- **Casas de sorte e revés**: sorte: "Ganhe R$X"; revés: "Pague R$X".
- Implementação de **compra e venda de imóveis entre jogadores**.
- Permitir que **jogadores falidos vendam propriedades** para continuar no jogo.

## Requisitos do Sistema
- **Java 8+**.
- Ambiente de desenvolvimento integrado (IDE) compatível com Java (Eclipse, IntelliJ IDEA, etc.).

## Instruções de Execução
1. Clone o repositório.
2. Compile e execute o projeto através de sua IDE Java preferida.
3. Siga as instruções no console para cadastrar os imóveis, jogadores e iniciar o jogo.

## Estrutura do Código
- **Tabuleiro**: Implementado como uma lista ligada circular.
- **Jogadores**: Gerenciados por uma estrutura separada, como uma lista ou array.
- **Casas Especiais**: Início, imóveis, imposto, restituição e outras interações descritas.

## Contribuição
Contribuições são bem-vindas! Para contribuir:
1. Faça um fork do projeto.
2. Crie um branch para suas funcionalidades (`git checkout -b feature/nova-funcionalidade`).
3. Faça commit de suas alterações (`git commit -m 'Adiciona nova funcionalidade'`).
4. Faça push para o branch (`git push origin feature/nova-funcionalidade`).
5. Abra um pull request.
