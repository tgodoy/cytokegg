#Getting started with CytoKegg

# Getting started with CytoKegg #

## The Index ##
CytoKegg Relies on an Index in order to store pathway data as well as expression datasets. The Index is created when the application is used for the first time when six model organisms will be indexed by default, however more can be indexed later on.

Organisms indexed by default:

  * Homo sapiens (human)
  * Mus musculus (mouse)
  * Rattus norvegicus (rat)
  * Danio rerio (zebrafish)
  * Drosophila melanogaster (fruit fly)

In order to access the index, go to Apps > CytoKegg > Repository. Once there, it is possible to rebuild the index and add another organism.

## Browse Pathways ##
CytoKegg can import pathways into Cytoscape by going to Apps > CytoKegg > Browse Pathways. Once there, select the organism as well as the pathway to visualize, then click the select button. CytoKegg will then import the pathway into Cytoscape.

## Find Pathways By Gene(s) ##
With CytoKegg, it is possible to find pathways in a particular dataset. A dataset is a structure that holds a group of genes and possibly gene expression changes under different conditions.

A dataset canbe imported from a file or a network.

### Import Dataset From File ###
To import a dataset from a file, go to Apps > CytoKegg > Find Pathways By Gene(s). Then click the **Import from File** button, select the file and then click **next**.

The file must be a ta separated file where the gene ids are in the first column and the expression values in the remaining ones. A example can be found [here](https://code.google.com/p/cytokegg/source/browse/trunk/sample%20files/sampledata).

### Import Dataset From Network ###
To import a dataset from an open network, go to Apps > CytoKegg > Find Pathways By Gene(s). Then click the **Import from Network** button. Once there, select the attribute that holds the gene name and optionally select those attributes that hold expression values. After that click next and select the pathway.