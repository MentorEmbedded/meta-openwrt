DESCRIPTION = "Unified Configuration Interface"
HOMEPAGE = "http://wiki.openwrt.org/doc/uci"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://uhttpd.c;beginline=1;endline=18;md5=758f7437f8a009878b38d879168c334e"

SRC_URI = "git://nbd.name/uhttpd.git;protocol=git;branch=master"
		   
SRCREV = "99f729378f69b2985c559bc8639b2edd06d75233"
S = "${WORKDIR}/git"

inherit cmake

PR="r1"

DEPENDS = "ubus lua5.1"

#FIXME: put plugins to the correct place
FILES_${PN} += "/usr/lib/*.so"


