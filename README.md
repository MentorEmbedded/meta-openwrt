# meta-openwrt
OpenWRT core as Yocto layer

Overview.

This is the layer containing most used recipes of Openwrt.
Openwrt has it`s own implementation of init, message bus, http server, web interface and sets of system utils and libraries.
Anyway it activly uses busybox, iptbales and other common linux components.
To get more information about OpenWrt please see: http://wiki.openwrt.org/doc/techref/architecture.
The main idea behind this layer was to allow using OE build system together with OpenWrt base packages set.

Build requirements.

This layer required:
* bitbake
* oe-core
* meta-openembedded mostly for lua/luajit

Build instructions.

1. mkdir sources
2. git clone git://git.openembedded.org/openembedded-core sources/oe-core -b dizzy
3. git clone git://git.openembedded.org/bitbake sources/oe-core/bitbake -b 1.26
4. git clone git://git.openembedded.org/meta-openembedded sources/meta-oe -b dizzy
5. git clone git@github.com:MentorEmbedded/meta-openwrt.git sources/meta-openwrt -b juci
6. TEMPLATECONF=../sources/meta-openwrt/conf . ./sources/oe-core/oe-init-build-env
7. bitbake  openwrt-image-base
