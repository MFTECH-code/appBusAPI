node {
    def resourceGroupName = 'rg-appbus-api'
    def resourceGroupLocation = 'brazilsouth'
    def appServicePlanName = 'sp-app-appbus-api-221006010914'
    def appServicePlanTier = 'FREE'
    def webAppName = 'app-appbus-api-221006010913'
    def webAppRuntime = '"java:17:Java SE:17"'
    def packagePath = 'target/api-api-0.0.1-SNAPSHOT.jar'
    
    stage('Extrair Codigo Fonte') {
        echo 'Obtendo o Código Fonte ...'
        checkout([$class: 'GitSCM', branches: [[name: '*/main']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], 
        userRemoteConfigs: [[url: 'https://github.com/MFTECH-code/appBusAPI']]])
    }

    stage('Build') {
        echo 'Empacotando o projeto...'
        bat 'mvn clean package'
    }

    stage('Credenciais Azure') {
        echo 'Obtendo credenciais...'
        withCredentials([usernamePassword(credentialsId: 'AzureService', 
        passwordVariable: 'AZURE_CLIENT_SECRET',
        usernameVariable: 'AZURE_CLIENT_ID')]) {
            echo 'Logando na Azure...'
            bat 'az login -u %AZURE_CLIENT_ID% -p %AZURE_CLIENT_SECRET%'
        }
    }

    stage('Criar Infra') {
        echo 'Criando o Grupo de Recursos...'
        bat "az group create --name $resourceGroupName --location $resourceGroupLocation"
        echo 'Criando Plano de Serviço...'
        bat "az appservice plan create --name $appServicePlanName --resource-group $resourceGroupName --sku $appServicePlanTier"
        echo 'Criando o Web App...'
        bat "az webapp create --name $webAppName --plan $appServicePlanName --resource-group $resourceGroupName --runtime $webAppRuntime"
    }
    
    stage('Deploy') {
        echo 'Realizando o Deploy na Azure...'
        bat "az webapp deploy --resource-group $resourceGroupName --name $webAppName --src-path $packagePath --type jar"
    }
}