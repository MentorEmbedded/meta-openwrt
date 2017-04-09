FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"
SRCREV = "23644e8f150baac0aa5199a6820234d8e7898231"
SRC_URI = "git://github.com/mkschreder/juci-lighttpd \
		file://lighttpd.init \
		file://etc \
		file://etc/certs \
		file://pkgconfig.patch \
	"
S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

PARALLEL_MAKE = ""

EXTRA_OECONF += "--libdir=/usr/lib/lighttpd \
	--sysconfdir=/etc/lighttpd \
	--enable-shared \
	--enable-static \
	--without-attr \
	--without-bzip2 \
	--without-fam \
	--without-gdbm \
	--without-ldap \
	--without-lua \
	--without-memcache \
	--without-mysql \
	--with-pcre \
	--without-valgrind \
	--with-websocket=ALL \	
	"

LDFLAGS_append = " -lpcre "
RDEPENDS_${PN} = ""

do_install () {
	DESTDIR=${D} oe_runmake install
	install -d ${D}/etc
	cp -a ${WORKDIR}/etc/* ${D}/etc/
	install -d ${D}/etc/init.d
	install -m 750 ${WORKDIR}/lighttpd.init ${D}/etc/init.d/lighttpd
	return 0;
}

FILES_${PN} += "/usr/lib/lighttpd/*"
