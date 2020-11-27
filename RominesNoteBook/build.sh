mkdocs build;
rm -r $CATALINA_HOME/webapps/note/;
mv site $CATALINA_HOME/webapps/note/;