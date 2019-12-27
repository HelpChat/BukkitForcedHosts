![testplugins.com](https://helpch.at/testplugins.png)
[![helpch.at/discord](https://helpch.at/banner.png)](https://helpch.at/discord)
![BFH](https://cdn.piggypiglet.me/bfh/title.png)
BFH is a simple plugin that allows you to configure commands that will be ran on a player, if they join with a specific host name. For example, if your server had two creative worlds, you could create a configuration for creative1.example.com and creative2.example.com, to join the player to the correct world. Or perhaps, send them a message if they join with a specific url.

![Configuration](https://cdn.piggypiglet.me/bfh/config.png)<br/>
The config will generate on first start, in a folder called BukkitForcedHosts, called hosts.yml. Inside, the format is extremely simple.
```yaml
example-com:
  - "tellraw %s {\"text\":\"Hello\"}"
```
example-com is your domain, the dash, automatically gets replaced with a . at runtime. The reason it doesn't use periods in the config, is because . is the path separator in bukkit.<br/>
The element accepts a list of strings, i.e. your commands. In each, %s will be replaced with the player name. If you use the player name more than once, instead reference it via %1$s after the first occurrence. For example:<br/>
`tellraw %s {\"text\":\"Hello %1$s\"}`

![Commands](https://cdn.piggypiglet.me/bfh/commands.png)<br/>
Only one command in BFH - `/bfh reload`. The permission for this is `bukkitforcedhosts.reload`.

**CI:** https://ci.piggypiglet.me/job/BukkitForcedHosts ![Jenkins](https://img.shields.io/jenkins/build/https/ci.piggypiglet.me/BukkitForcedHosts)<br/>
**NOTE:** File size is due to this plugin's usage of [RPF](https://github.com/PiggyPiglet/Framework), a framework you should definitely check out if you're a developer.