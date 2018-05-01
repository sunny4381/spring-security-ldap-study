Trivial LDAP Login Study with Spring Security
====

## Test LDAP Server

To launch test ldap server, run below commands:

~~~
$ docker pull osixia/openldap
$ docker pull osixia/phpldapadmin                                                                 
$ docker run --name openldap --hostname openldap -p 10389:389 --detach osixia/openldap
$ docker run --name phpldapadmin -p 18080:80 --hostname phpldapadmin --link openldap:ldap-host --env PHPLDAPADMIN_LDAP_HOSTS=ldap-host --env PHPLDAPADMIN_HTTPS=false --detach osixia/phpldapadmin
~~~

And then open <http://localhost:18080/> in your browser, import these ldif

~~~
version: 1

dn: ou=people,dc=example,dc=org
objectclass: top
objectclass: organizationalUnit
ou: people

dn: uid=admin,ou=people,dc=example,dc=org
cn: Kazuki Shimizu
objectclass: top
objectclass: person
objectclass: organizationalPerson
objectclass: inetOrgPerson
sn: Kazuki
uid: admin
userpassword: {CRYPT}7pnoyta7lRz7M

dn: uid=user,ou=people,dc=example,dc=org
cn: Taro Yamada
objectclass: top
objectclass: person
objectclass: organizationalPerson
objectclass: inetOrgPerson
sn: Taro
uid: user
userpassword: {CRYPT}5yE50Zf2Dqg2o

dn: ou=groups,dc=example,dc=org
objectclass: top
objectclass: organizationalUnit
ou: groups

dn: cn=admin,ou=groups,dc=example,dc=org
cn: admin
objectclass: groupOfUniqueNames
objectclass: top
uniquemember: uid=admin,ou=people,dc=example,dc=org

dn: cn=user,ou=groups,dc=example,dc=org
cn: user
objectclass: groupOfUniqueNames
objectclass: top
uniquemember: uid=admin,ou=people,dc=example,dc=org
uniquemember: uid=user,ou=people,dc=example,dc=org
~~~

By importing these ldif, create these users and groups

| User ID | Password | Full Name | Group (= Role)   |
|---------|----------|----------------|-------------|
| admin   | password | Kazuki Shimizu | admin, user |
| user    | password | Taro Yamada    | user        |

## About This Study

- Use LDAP Login with Spring Security
- Use Custom Login Form
- I18n Login Form
- I18n Spring Security Error Messages

## Related Work

- [Spring Security(Spring Boot) + OpenLDAPでLDAP認証してみる](https://qiita.com/kazuki43zoo/items/6bef663e2a885d8a0f16)
- [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
- [Thymeleaf + Spring で i18n ～１～](https://dev.classmethod.jp/server-side/java/thymeleaf-spring-i18n-1/)
