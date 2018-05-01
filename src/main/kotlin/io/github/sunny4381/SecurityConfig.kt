package io.github.sunny4381

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.ldap.userdetails.PersonContextMapper

@Configuration
open class SecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http : HttpSecurity) {
        http.authorizeRequests()
                .antMatchers("/**").authenticated() // 全てのパスに対して「認証済み」を要求するアクセスポリシーを設定する
                .and()
                .logout().permitAll() // ログアウト機能を有効化
                .and()
                .formLogin().loginPage("/login").permitAll() // フォームログイン機能を有効化
    }

    override fun configure(auth : AuthenticationManagerBuilder) {
        // LDAP認証を有効化
        auth.ldapAuthentication()
                // ユーザーの識別名(DN=Distinguished Name)パターンを指定
                // {0}にはログインフォームで入力したusernameが埋め込まれる
                .userDnPatterns("uid={0},ou=people")
                // グループ（ロール）を検索するユニットを指定
                .groupSearchBase("ou=groups")
                // LDAPのデータソースを指定
                .contextSource()
                // 接続URLを指定
                .url("ldap://localhost:10389/dc=example,dc=org")
                // LDAPに接続するためのユーザーの識別名を指定
                .managerDn("cn=admin,dc=example,dc=org")
                // LDAPに接続するためのパスワードを指定
                .managerPassword("admin")
                .and()
                // UserDetailsを生成するオブジェクトを指定
                // デフォルトはLdapUserDetailsMapperが利用されるが、本エントリーでは氏名(cn=Common Name)が参照できるPersonContextMapperを利用
                // → 要件にあった実装を選択する or 実装する
                .userDetailsContextMapper(PersonContextMapper())
    }
}
