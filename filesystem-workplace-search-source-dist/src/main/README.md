# Workplace Search : Custom Source - filesystem-workplace-search-source

This project is intended to be a Custom API Source for Elastic's Workplace Search.


Before using this product, you'll need to have first [installed and configured Workplace Search](https://www.elastic.co/guide/en/workplace-search/current/workplace-search-install.html).
After that, you'll need to [create a Custom API Source](https://www.elastic.co/guide/en/workplace-search/current/workplace-search-custom-api-sources.html#create-custom-source).

Copy the resulting **Access Token** and **Key** and set them in the `config/source.yml` of this project like:

    access_token: 3a423c597442eddb09baad64793ff342fc0aa6da357f5227888d44b3386cf722
    content_source_key: 5e87603bf74c32fe5fa37d86

Once your configuration is set, you are ready to sync your content!


To sync your content, run:

    bin/sync
