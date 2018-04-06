rm(list = rm())

dados  <- read.csv2("data/data-ag-bin-real.txt")

library(plyr)
variavel  <- count(dados,c("Teste"))

min(dados$FO)