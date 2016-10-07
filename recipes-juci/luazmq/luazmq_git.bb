DESCRIPTION = "ZeroMQ binding for Lua. The ZeroMQ lightweight messaging kernel is a library which extends the standard socket interfaces with features traditionally provided by specialised messaging middleware products."
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://README.md;md5=a73e58b960d2a174cbaa53df74ebb3e5"

SRC_URI = "git://github.com/soulik/luazmq.git;protocol=git;branch=master;name=main"
SRCREV_main = "5291bbd3de5a88ccfcd457c6233ed8fb96dbcb3f"

SRC_URI += "git://github.com/soulik/lutok2.git;protocol=git;branch=master;name=lutok;destsuffix=${WORKDIR}/git/dependencies/lutok2"
SRCREV_lutok = "d8f6eecf87641f3765a92a903c7558afd47eec00"

SRC_URI += "file://cmakefiles.patch"
SRC_URI += "file://luajit.patch"

S = "${WORKDIR}/git"
B = "${S}"

inherit cmake

DEPENDS = "zeromq"

FILES_${PN} += "/usr/lib/lua/5.1/* /usr/share/lua/5.1/*"
FILES_${PN}-dbg += "/usr/lib/lua/5.1/.debug/*"

do_install () {
	mkdir -p ${D}/usr/lib/lua/5.1
	mkdir -p ${D}/usr/share/lua/5.1
	install -m 622 ${B}/arm/lib/luazmq.so ${D}/usr/lib/lua/5.1/
	install -m 622 ${B}/arm/lib/zmq.lua   ${D}/usr/share/lua/5.1/
}
