#bin/bash
echo "Download i18n files from tolgee";

curl -o i18n.zip -J -L --request GET 'https://app.tolgee.io/v2/projects/export?languages=de-DE,en-US&zip=true&format=JSON' \
--header 'X-API-Key: '$TOLGEE_DOWNLOAD_API_KEY

# Verify that i18n.zip exist
 if [ ! -f i18n.zip ]; then
    echo "i18n.zip does not exist"
    exit 1
fi

echo "Unzip i18n files"
unzip -o i18n.zip -d src/i18n
rm i18n.zip

echo "i18n successfully downloaded and installed"
