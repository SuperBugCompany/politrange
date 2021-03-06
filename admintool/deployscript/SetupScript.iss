; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

#define MyAppName "AdminTool"
#define MyAppVersion "0.0.1beta"
#define MyAppPublisher "SuperBugCompany"
#define MyAppURL "https://superbugcompany.github.io/politrange/website/"
#define MyAppExeName "admintool.exe"

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{928EAA28-3B0A-4FBE-8CCF-890917345B13}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
;AppVerName={#MyAppName} {#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
AppSupportURL={#MyAppURL}
AppUpdatesURL={#MyAppURL}
DefaultDirName={pf}\{#MyAppName}
DefaultGroupName=PolitRange
AllowNoIcons=yes
InfoBeforeFile=D:\politrange\admintool\deployscript\requirements.txt
OutputBaseFilename=SetupPolitRangeAdminTool
SetupIconFile=D:\politrange\admintool\src\resources\admintool.ico
;lzma
Compression= lzma
SolidCompression=yes

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"
Name: "russian"; MessagesFile: "compiler:Languages\Russian.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"
Name: "quicklaunchicon"; Description: "{cm:CreateQuickLaunchIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked; OnlyBelowVersion: 0,6.1

[Files]
Source: "D:\politrange\admintool\out\artifacts\admintool\bundles\admintool\admintool.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "D:\politrange\admintool\out\artifacts\admintool\bundles\admintool\admintool.ico"; DestDir: "{app}"; Flags: ignoreversion
Source: "D:\politrange\admintool\out\artifacts\admintool\bundles\admintool\msvcp100.dll"; DestDir: "{app}"; Flags: ignoreversion
Source: "D:\politrange\admintool\out\artifacts\admintool\bundles\admintool\msvcr100.dll"; DestDir: "{app}"; Flags: ignoreversion
;Source: "D:\politrange\admintool\out\artifacts\admintool\bundles\admintool\msvcr100.dll"; DestDir: "{app}"; Flags: ignoreversion
Source: "D:\politrange\admintool\out\artifacts\admintool\bundles\admintool\packager.dll"; DestDir: "{app}"; Flags: ignoreversion
Source: "D:\politrange\admintool\out\artifacts\admintool\bundles\admintool\app\*"; DestDir: "{app}\app\"; Flags: ignoreversion recursesubdirs createallsubdirs
Source: "D:\politrange\admintool\out\artifacts\admintool\bundles\admintool\runtime\*"; DestDir: "{app}\runtime\"; Flags: ignoreversion recursesubdirs createallsubdirs
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{group}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"
Name: "{group}\{cm:UninstallProgram,{#MyAppName}}"; Filename: "{uninstallexe}"
Name: "{commondesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; Tasks: desktopicon
Name: "{userappdata}\Microsoft\Internet Explorer\Quick Launch\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; Tasks: quicklaunchicon

[Run]
Filename: "{app}\{#MyAppExeName}"; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"; Flags: nowait postinstall skipifsilent

