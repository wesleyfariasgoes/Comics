### Visão Geral do Projeto

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=%20DESENVOLVIDO&color=GREEN&style=for-the-badge)

<img src="https://github.com/wesleyfariasgoes/images/blob/main/Screenshot_20250921_093256.png" width="210">

Este `README` documenta as mudanças arquiteturais e tecnológicas implementadas na *branch* `mvvm`, comparadas à *branch* `master`.

---

### Sumário das Mudanças

A principal alteração foi a migração do projeto de uma arquitetura **VIP (Presenter/View/Interactor)** baseada em **XML** para uma arquitetura moderna **MVVM**, utilizando as seguintes tecnologias e conceitos:

| Antes (Branch `master`)                                      | Agora (Branch `mvvm`)                                                  |
| ------------------------------------------------------------ | ---------------------------------------------------------------------- |
| Arquitetura VIP                                              | **MVVM (Model-View-ViewModel)** |
| Layouts em XML                                               | **Jetpack Compose** (Desenvolvimento de UI declarativa)                |
| Callback/Interface Listener (Comunicação Assíncrona)         | **Coroutines** e **Kotlin Flow** (Gerenciamento de concorrência)       |
| Injeção de Dependência Manual                                | **Hilt Dagger** (Injeção de dependência simplificada)                  |
| Estrutura de Camadas Clássica (Repository, Interactor, Presenter) | **Clean Architecture** (Data, Domain, Presentation)                    |

---

### Detalhes da Arquitetura MVVM e Clean Architecture

O novo projeto segue rigorosamente os princípios de **Clean Architecture**, dividindo o código em três camadas principais para garantir **separação de responsabilidades** e **maior testabilidade**.

1.  **`data`**:
    * **Repository Implementation**: Implementa a interface do repositório da camada de domínio. Responsável por buscar dados de fontes externas (como a API da TMDb) e mapeá-los para entidades do domínio.
    * **API Service**: Define as chamadas de rede para o Retrofit.
    * **Data Models (DTOs)**: Representa a estrutura de dados exata retornada pela API.

2.  **`domain`**:
    * **Use Cases**: Contém a lógica de negócio principal do aplicativo. Ele interage com o repositório para obter dados.
    * **Repository Interface**: Define o contrato para a camada de dados. A camada de domínio não se preocupa com a origem dos dados, apenas com a sua manipulação.
    * **Entities**: Representa os modelos de dados centrais do aplicativo.

3.  **`presentation`**:
    * **ViewModel**: Atua como a ponte entre a View (Compose) e as camadas de domínio/dados. Ele gerencia o estado da UI, lida com a lógica de apresentação e expõe dados através do **Kotlin Flow**.
    * **Composable UI**: As telas do aplicativo, criadas com **Jetpack Compose**. A View observa o estado do `ViewModel` e se atualiza automaticamente.

---

### Principais Tecnologias Utilizadas

* **Jetpack Compose**: Substitui os layouts em XML, permitindo um desenvolvimento de UI mais rápido, intuitivo e com menos código.
* **Kotlin Coroutines & Flow**: Oferece uma abordagem moderna para gerenciar tarefas assíncronas, garantindo que a UI não seja bloqueada durante chamadas de rede. O Flow é usado para emitir dados de forma reativa, o que se integra perfeitamente ao `ViewModel`.
* **Hilt Dagger**: Simplifica drasticamente a injeção de dependência. O Hilt gerencia o ciclo de vida das dependências e elimina a necessidade de código *boilerplate*, tornando o projeto mais limpo e fácil de manter.
* **Retrofit**: Biblioteca para comunicação com a API REST da TMDb. Utilizado em conjunto com o `Gson` para a conversão de JSON para objetos Kotlin.
* **Coil**: Biblioteca para carregamento de imagens de forma assíncrona, otimizada para o Jetpack Compose.

---

### Como Rodar o Projeto

1.  Clone o repositório.
2.  Mude para a *branch* `mvvm`: `git checkout mvvm`
3.  Abra o projeto no Android Studio.
4.  Execute-o em um emulador ou dispositivo físico.
