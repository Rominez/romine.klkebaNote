# How To Use
## Install chocolatey
Use powershell(Administrator)
```
Set-ExecutionPolicy Bypass -Scope Process -Force; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))
```
check
```
choco -V
```
## Install MkDocs
```
choco install mkdocs
```
## Run
```
mkdocs serve
```
If not work, try
```
python -m mkdocs serve
```
