import com.puravida.MultipleDevicesWindowDisplayHandler

application {
    title = 'Griffon DNIe'
    startupGroups = ['griffdnifx']
    autoShutdown = true
}
mvcGroups {
    // MVC Group for "griffdnifx"
    'griffdnifx' {
        model      = 'com.puravida.GriffdnifxModel'
        view       = 'com.puravida.GriffdnifxView'
        controller = 'com.puravida.GriffdnifxController'
    }

    'login'{
        model      = 'com.puravida.LoginModel'
        view       = 'com.puravida.LoginView'
        controller = 'com.puravida.LoginController'
    }

    'rest'{
        model      = 'com.puravida.RestModel'
        view       = 'com.puravida.RestView'
        controller = 'com.puravida.RestController'
    }

    'agreement'{
        model      = 'com.puravida.AgreementModel'
        view       = 'com.puravida.AgreementView'
        controller = 'com.puravida.AgreementController'
    }
}

windowManager {
    defaultHandler = new MultipleDevicesWindowDisplayHandler()
}
