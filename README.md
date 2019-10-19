# MoreGameRules

A Minecraft library mod built for Fabric Loader that introduces an API for registering
new boolean rules and int rules, and provides implementations for double rules, float rules
and string rules.

## Buildscript Setup
**Add the JitPack maven to your project repositories:**
```groovy
maven {
  name = 'jitpack'
  url = 'https://jitpack.io'
}
```
---
**Add the library to your project dependencies:**
- `modImplementation` ensures the library is present at compile time
- `include` packages the library within your mod jar when it is built
```groovy
modImplementation 'com.github.ChloeDawn:MoreGameRules:1.0.0'
include 'com.github.ChloeDawn:MoreGameRules:1.0.0'
```
