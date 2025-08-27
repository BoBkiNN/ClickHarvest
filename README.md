[![See on Modrinth - ClickHarvest](https://img.shields.io/badge/See_on_Modrinth-ClickHarvest-2ea44f?logo=modrinth)](https://modrinth.com/plugin/clickharvest)
# ClickHarvest
Very small plugin for harvesting crops using right click

* This plugin can help players easily harvest crops that can be useful for survival servers.
* When clicked, crop automatically replants and then its drop drops.
* While sneaking, auto harvest is disabled

Other features:
* whitelist/blacklist worlds from harvesting this way
* WorldGuard support

Commands:
* `/clickharvestreload` - reloads configuration. Permission: `clickharvest.reload`
* `/clickharvest` - enables/disables plugin for player that executed command

### Default config:
```yaml
# WorldGuard compatibility
worldguard:
  enabled: true
  flag: interact # flag to test

# filter by world
worlds:
  mode: blacklist # or whitelist
  list: [] # list of world names
```