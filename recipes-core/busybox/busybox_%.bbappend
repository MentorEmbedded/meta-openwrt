# Example use of configuration fragments for busybox, which uses the same
# mechanism as the linux-yocto kernel recipe.
#
# The entries here will override any entries in the base busybox recipe
#
# More details can be found in the Kernel Dev Manual
# http://www.yoctoproject.org/docs/current/kernel-dev/kernel-dev.html#changing-the-configuration
FILESEXTRAPATHS_prepend := "${THISDIR}/files:"

SRC_URI += " \
	    file://lock.cfg \
	    file://220-add_lock_util.patch \
	    file://250-date-k-flag.patch \
           "

do_install_append () {
	rm ${D}/usr/share/udhcpc/default.script
}
