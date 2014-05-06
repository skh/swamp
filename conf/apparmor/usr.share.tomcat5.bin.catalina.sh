# vim:syntax=apparmor
# Last Modified: Sat Dec  8 02:39:03 2007
#include <tunables/global>

/usr/share/tomcat5/bin/catalina.sh {
  #include <abstractions/base>
  #include <abstractions/bash>
  #include <abstractions/nameservice>
  #include <abstractions/user-tmp>

  /bin/bash ixr,
  /bin/grep ixr,
  /bin/sed ixr,
  /bin/touch ixr,
  /bin/uname ixr,
  /dev/random r,
  /dev/tty rw,
  /etc/java/java.conf r,
  /etc/ld.so.cache mr,
  /etc/sysconfig/clock r,
  /etc/tomcat5/base/** rw,
  /proc/*/stat r,
  /proc/net/if_inet6 r,
  /proc/net/ipv6_route r,
  /proc/version r,
  /root/tomcat_apparmor/tomcat_5_0 r,
  /srv/www/tomcat5/base/** mr,
  /srv/www/tomcat5/base/webapps/webswamp/WEB-INF/conf/logs mrw,
  /srv/www/tomcat5/base/webapps/webswamp/WEB-INF/conf/logs/* mrw,
  /srv/www/tomcat5/base/webapps/webswamp/resources/workflows/** mrw,
  /srv/www/tomcat5/base/webapps/webswamp/templates/app/** mrw,
  /srv/www/tomcat5/base/webapps/webswamp/var/statistics/* mrw,
  /srv/www/tomcat5/base/webapps/webswamp/var/upload/* mrw,
  /tmp/** lmrw,
  /usr/bin/build-classpath ixr,
  /usr/bin/dirname ixr,
  /usr/bin/find ixr,
  /usr/bin/readlink ixr,
  /usr/lib/jvm/** ixr,
  /usr/share/java r,
  /usr/share/java-utils/* r,
  /usr/share/java/** mr,
  /usr/share/tomcat5/** mr,
  /usr/share/zoneinfo r,
  /var/cache/tomcat5/base/** rw,
  /var/log/tomcat5/base/* w,
  /var/run/tomcat5.pid w,

  ^DEFAULT {
    #include <abstractions/base>
    #include <abstractions/fonts>
    #include <abstractions/nameservice>

    /etc/SuSE-release r,
    /etc/ld.so.cache mr,
    /lib/ld-2.4.so ixr,
    /proc/*/fd r,
    /proc/*/maps r,
    /srv/www/tomcat5/base/** mr,
    /srv/www/tomcat5/base/webapps/webswamp/WEB-INF/conf/workflows/** mrw,
    /srv/www/tomcat5/base/webapps/webswamp/resources/workflows/** mrw,
    /srv/www/tomcat5/base/webapps/webswamp/templates/app/docs/workflows/** mrw,
    /srv/www/tomcat5/base/webapps/webswamp/var/graphs/* mrw,
    /srv/www/tomcat5/base/webapps/webswamp/var/statistics/* mrw,
    /srv/www/tomcat5/base/webapps/webswamp/var/upload/* mrw,
    /usr/X11R6/lib/X11/fonts r,
    /usr/X11R6/lib/X11/fonts/** mr,
    /usr/X11R6/lib/lib*so* mr,
    /usr/bin/dot ixr,
    /usr/lib/graphviz/* mr,
    /usr/lib/jvm/** mr,
    /usr/share/ghostscript/fonts/** mr,
    /usr/share/java/* mr,
    /usr/share/java/** r,
    /var/cache/tomcat5/base/temp/** rw,
    /var/cache/tomcat5/base/work/** rw,
    /var/log/tomcat5/base/* w,
    /var/log/tomcat5/base/velocity.log rw,
  }
}
