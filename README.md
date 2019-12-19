# Game Rules

A library written for Fabric that allows for registration and observation of game rules.
Also includes rule implementations for doubles, enums, floats, and strings. The library
is lightweight and intended to be packaged within compiled mod jars rather than be an
external dependency.

#### Developer Setup

**Add JitPack's maven to your project repositories:**
```groovy
maven { url 'https://jitpack.io' }
```
---
**Add the library to your project dependencies:**
- `modImplementation` ensures the library is present at compile time
- `include` packages the library within your mod jar when it is built
```groovy
modImplementation 'io.github.chloedawn:gamerules:0.1.0'
include 'io.github.chloedawn:gamerules:0.1.0'
```

#### Kotlin API Notice

When using the Kotlin API of this library, please be aware that the Kotlin standard
libraries are not provided natively, it is dependant on the library user to provide
them at runtime and in the project environment. The library is configured this way
in order to create the smallest package footprint possible for Java-only dependants.
