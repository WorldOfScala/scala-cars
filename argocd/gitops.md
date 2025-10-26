# scala-cars-gitops


## Publish the application GitOps to the GitHub

The [GitOps](GitOps/) folder contains the ArgoCD application configuration. You can publish this folder to a GitHub repository by running the following commands:

1. Create a new repository in GitHub.
```bash
cd GitOps
gh repo create cheleb/scala-cars-gitops --private
git remote add origin git@github.com:cheleb/scala-cars-gitops.git
```

3. Push the GitOps folder to the repository.
```bash
git push -u origin master
```

2. Add public key to the repository settings.
