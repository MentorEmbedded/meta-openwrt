# meta-openwrt
OpenWRT core as Yocto layer

Overview.

This is the layer containing most used recipes of Openwrt.
Openwrt has it`s own implementation of init, message bus, http server, web interface and sets of system utils and libraries.
Anyway it activly uses busybox, iptbales and other common linux components.
To get more information about OpenWrt please see: http://wiki.openwrt.org/doc/techref/architecture.
The main idea behind this layer was to allow using OE build system together with OpenWrt base packages set.

Build requirements.

This layer requiired:
* bitbake
* oe-core
* meta-openembedded mostly for lua/luajit

Build instructions.

1. mkdir sources
2. git clone git://git.openembedded.org/openembedded-core sources/oe-core -b daisy
3. git clone git://git.openembedded.org/bitbake sources/oe-core/bitbake -b 1.22
4. git clone git://git.openembedded.org/meta-openembedded sources/meta-oe -b daisy
5. git clone git@github.com:MentorEmbedded/meta-openwrt.git sources/meta-openwrt
6. TEMPLATECONF=../sources/meta-openwrt/conf . ./source/oe-core/oe-init-build-env
7. bitbake  openwrt-image-base
