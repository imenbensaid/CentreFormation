function deleteformation(idf,ide){
   fetch("/etudiant/delete-formation/"+idf+"/"+ide, {
      method: "POST",
      body: JSON.stringify({ noteId: noteId }),
    }).then((_res) => {
      window.location.href = "/";
    });
}