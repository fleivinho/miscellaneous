# MiscellaneousAPI (framework)

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

Bem-vindo ao MiscellaneousAPI, uma API versátil para recursos diversos que podem ser úteis em projetos Spigot. Esta API foi desenvolvida para facilitar a integração de uma variedade de funcionalidades em servidores, economizando tempo e esforço de desenvolvimento.

## Pré-requisitos

Antes de começar a usar a MiscellaneousAPI, você precisará ter o seguinte software instalado:

- **Spigot**: A API é projetada para ser usada em servidores 1.8x a 1.19x
- **Java**: Certifique-se de que você tenha uma versão compatível do Java instalada.

## Instalação

Você pode baixar e aplicar em seu projeto como também poderá utiliza-lo como um plugin para que não seja necessário baixar o projeto.

## Como Usar

Aqui está um exemplo de como usar a MiscellaneousAPI em seu projeto:

```java
// Exemplo de código em Java
import org.miscellaneous.MiscellaneousAPI;
import org.miscellaneous.apis.classloader.ClassLoader;

public class MeuPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        instance = this;

        MiscellaneousAPI miscellaneousAPI = MiscellaneousAPI.getAPI();
        //                                = new MiscellaneousAPI();

        ClassLoader classLoader = miscellaneousAPI.getClassLoader(this);
        classLoader.init("org.miscellaneous.plugin.commands").loadCommands();
        classLoader.init("org.miscellaneous.plugin.events").loadEvents();
    }
}
