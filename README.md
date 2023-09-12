# MiscellaneousAPI (framework)

[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

Bem-vindo ao MiscellaneousAPI, uma API versátil para recursos diversos que podem ser úteis em projetos Spigot. Esta API foi desenvolvida para facilitar a integração de uma variedade de funcionalidades em servidores, economizando tempo e esforço de desenvolvimento.

## Colaboradores

Sinta-se a vontade para colaborar com o projeto! ♥

## Pré-requisitos

Antes de começar a usar a MiscellaneousAPI, você precisará ter o seguinte software instalado:

- **Spigot**: A API é projetada para ser usada em servidores 1.8x a 1.19x
- **Java**: Certifique-se de que você tenha uma versão compatível do Java instalada.

## Instalação

Você pode baixar e aplicar em seu projeto como também poderá utiliza-lo como um plugin para que não seja necessário baixar o projeto.

## Como Usar

Aqui estão alguns exemplos de como usar a MiscellaneousAPI em seu projeto:
### Carregando classes de comandos e eventos

```java
import org.miscellaneous.MiscellaneousAPI;
import org.miscellaneous.apis.classloader.MiscellaneousClassLoader;

public class MeuPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        MiscellaneousAPI miscellaneousAPI = new MiscellaneousAPI();
        MiscellaneousClassLoader classLoader = miscellaneousAPI.getClassLoader(this);
        classLoader.load("org.miscellaneous.plugin.commands",
                MiscellaneousClassLoader.LoaderType.COMMAND);
        classLoader.load("org.miscellaneous.plugin.events",
                MiscellaneousClassLoader.LoaderType.LISTENER);
    }
}
```

### Criando um comando

```java
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.miscellaneous.apis.itemcreator.MiscellaneousItemCreator;
import org.miscellaneous.apis.location.MiscellaneousLocationSerializer;
import org.miscellaneous.frameworks.command.MiscellaneousCommand;
import org.miscellaneous.frameworks.command.MiscellaneousSender;
import org.miscellaneous.plugin.MiscellaneousPlugin;

public class MiscCommand extends MiscellaneousCommand {

    public MiscCommand() {
        super("misc");
        setAliases("miscellaneous");
    }

    @Override
    public boolean onExecute(MiscellaneousSender commandSender, String label, String[] args) {
        MiscellaneousPlugin miscellaneousPlugin = MiscellaneousPlugin.miscellaneous();

        commandSender.sendMessage("§aEste servidor é aprimorado com §f" + miscellaneousPlugin.getName() + " v" + miscellaneousPlugin.getDescription().getVersion() + "§a!");
        commandSender.sendMessage("§aNosso projeto: §fhttps://github.com/fleivinho/miscellaneous");

        if (commandSender.isPlayer()) {
            Player player = commandSender.getPlayer();
            player.getInventory().addItem(new MiscellaneousItemCreator(Material.DIAMOND).setDisplayName("§bDiamante Lindo!").getItemStack());
            player.sendMessage("§eSua localização serialize: §f" + MiscellaneousLocationSerializer.serializeLocation(player.getLocation()));
        }

        return true;
    }
}
