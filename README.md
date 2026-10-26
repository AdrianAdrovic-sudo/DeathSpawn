# DeathSpawn

A Paper 26.3 plugin that lets admins set where players respawn after death if they have no bed or respawn anchor.

## Commands
| Command | Description |
|---|---|
| `/ds set` | Set the death spawn to your location |
| `/ds info` | Show the current death spawn |
| `/ds tp` | Teleport to the death spawn |
| `/ds clear` | Remove it (vanilla respawn) |

Permission: `deathspawn.admin` (ops by default)

## Building
Requires Java 25.
```
./gradlew build
```
The jar is in `build/libs/`.
