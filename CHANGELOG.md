# rimfrost-service-handlaggning changelog

Changelog of rimfrost-service-handlaggning.

## 0.3.0 (2026-04-02)

### Bug Fixes

-  Add support for ProduceratResultatRef ([0ed39](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/0ed39d7dd26a990) Lars Persson)  
-  Set aktivitetId on uppgift in PUT response ([316a1](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/316a115628fde8c) Lars Persson)  
-  Add support for beslut being included with Yrkande ([58763](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/58763490e0badaa) Lars Persson)  
-  Fix bugs introduced by latest refactor of service ([d85e6](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/d85e63ec2d607f7) Lars Persson)  
-  Bump openapi version ([2115a](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/2115a7b5cdacc0f) Lars Persson)  
-  Fix bugs introduced by latest refactor of service ([1c4b8](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/1c4b82e61c005f3) Lars Persson)  
-  added id to vah.yaml (#20) ([bdff9](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/bdff9bf66fff4a4) NilsElveros)  
-  Add support for sending process request over different topics based on formanstyp ([4c7ad](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/4c7ad7a6a7e71f1) Lars Persson)  
-  Replace hardcoded values with yaml based ones ([edef2](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/edef299c18e3eca) Lars Persson)  
-  allow update of ersattnings status (#16) ([dfb27](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/dfb276b1e37eddd) NilsElveros)  

### Other changes

**Feat/refactor handlaggning service (#19)**

* feat: refactor handlaggning service with new objectmodel 
* added repos and some more stuff 
* fix: avslagsanledning and yrkandestatus on producceraderesultat 

[be542](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/be542a04c3b34f8) NilsElveros *2026-03-19 14:05:48*


## 0.2.3 (2026-03-04)

### Bug Fixes

-  rename handlaggning servicename ([47cc0](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/47cc0cb3609c927) Ulf Slunga)  
-  rename handlaggning ([a8c05](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/a8c05e76e0f6a00) Ulf Slunga)  

## 0.2.2 (2026-03-03)

### Bug Fixes

-  Bump to trigger release flow ([49e4e](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/49e4e07b052c92a) Lars Persson)  
-  Avslagsanledning as nullable (#12) ([f2164](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/f21645b393f03c8) NilsElveros)  

## 0.2.1 (2026-02-26)

### Bug Fixes

-  ersattning som endpoint i PATCH. KundbehovsflodeEntity builder, lägger till from(kundbehovsflode) ([bffcb](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/bffcb618018a8ba) Ulf Slunga)  

## 0.2.0 (2026-02-24)

### Features

-  Split put into two operations, patch and put ([7aeaa](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/7aeaa2b215d562e) Lars Persson)  
-  FKPOC-247 fix lagrum, regel, specifikation for PUT request (#8) ([4400c](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/4400cff6adb88a8) NilsElveros)  

### Other changes

**update openapi version (#10)**


[b178f](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/b178f9de1db9ee3) NilsElveros *2026-02-24 07:29:47*


## 0.1.0 (2026-01-15)

### Features

-  add healthcheck ([7326f](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/7326f573c0f9dbd) Nils Elveros)  

### Bug Fixes

-  caps logger ([d7919](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/d7919ec30d73985) Nils Elveros)  
-  update to openapi 1.3.2 ([06641](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/06641d8ea09b58e) Nils Elveros)  

## 0.0.3 (2025-12-08)

### Bug Fixes

-  missade commits ([67c5a](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/67c5a9d90905e13) Ulf Slunga)  

## 0.0.2 (2025-12-08)

### Bug Fixes

-  spotless apply ([c2eb8](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/c2eb8621828d0df) Ulf Slunga)  
-  Stöd för att skicka avslutande Vah flode done message ([65703](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/65703c5504d600c) Ulf Slunga)  
-  apply spotless ([7597e](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/7597ee256f86c44) David Söderberg)  
-  add missing ersattning to kundbehov and use correct variable for formanstyp ([cfa49](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/cfa491213ba4a03) David Söderberg)  

## 0.0.1 (2025-12-04)

### Features

-  send VAH kafka message when kundeflode created ([3ff60](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/3ff607a310eedf5) Nils Elveros)  
-  add functioning version of kundbehov API ([1dd7f](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/1dd7fa2c571b087) David Söderberg)  
-  add java code ([c393a](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/c393a2250da3e94) David Söderberg)  

### Bug Fixes

-  removed avslutadTS null ([46a7b](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/46a7b5aafcd0432) Nils Elveros)  

### Other changes

**Removed system println**


[8382b](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/8382b113cc2b5c3) Nils Elveros *2025-12-04 07:49:33*

**Initial commit**


[9b1f5](https://github.com/Forsakringskassan/rimfrost-service-handlaggning/commit/9b1f584ea332753) davidsoderberg-ductus *2025-11-21 13:30:13*


